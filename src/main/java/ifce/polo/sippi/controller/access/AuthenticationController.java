package ifce.polo.sippi.controller.access;

import static ifce.polo.sippi.service.ContextProvider.getCredentials;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import ifce.polo.sippi.controller.AbstractController;
import ifce.polo.sippi.dto.FormMetadata;
import ifce.polo.sippi.dto.access.AccessBadge;
import ifce.polo.sippi.dto.access.AuthResponse;
import ifce.polo.sippi.dto.access.LoginFooter;
import ifce.polo.sippi.dto.access.LoginRequest;
import ifce.polo.sippi.dto.access.ProfileSetup;
import ifce.polo.sippi.dto.access.ResetPassword;
import ifce.polo.sippi.model.config.EditavelPolo;
import ifce.polo.sippi.repository.config.EditavelPoloRepository;
import ifce.polo.sippi.security.JwtTokenProvider;
import ifce.polo.sippi.security.SecurityConstants;
import ifce.polo.sippi.security.UserCredentials;
import ifce.polo.sippi.service.access.AccessProvider;
import ifce.polo.sippi.service.access.UsuarioService;
import ifce.polo.sippi.service.validation.ValidationService;

@RestController
@RequestMapping("/v1")
public class AuthenticationController extends AbstractController implements SecurityConstants
{
    @Autowired private ValidationService validationService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired private UsuarioService usuarioService;

    @Autowired private EditavelPoloRepository editavelPoloRepository;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/login/metadata")
    public ResponseEntity<?> getLoginMetadata(@RequestParam(required = false) String resource)
    {
        if ("footer".equals(resource))
        {
            EditavelPolo config = editavelPoloRepository.findById((short) 1).orElse(null);
            return ok(jsonMapper.convertValue(new LoginFooter(config), JsonNode.class));
        }

        return ok(new FormMetadata().setRules(validationService.generateRules(LoginRequest.class)));
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        return generateToken((UserCredentials) auth.getPrincipal());
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/auth/profile")
    public ResponseEntity<?> setProfile(@RequestBody ProfileSetup profile)
    {
        if (profile == null || profile.getProfileName() == null) {
            return badRequest("Missing required parameters");
        }

        AccessProvider provider = usuarioService.getAccessProvider(profile.getProfileName());

        if (provider == null) {
            return notFound("Profile not found");
        }

        UserCredentials credentials = getCredentials();

        if (ADMIN.equals(profile.getProfileName()))
        {
            if (!credentials.isAdmin()) {
                return forbidden("Unauthorized profile");
            }

            credentials.setProfile(ADMIN, null, provider.getAuthorities());
        }
        else if (profile.getProfileId() == null) {
            return badRequest("Missing required parameters");
        }
        else if (provider.authorize(credentials.getPersonId(), profile.getProfileId())) {
            credentials.setProfile(profile.getProfileName(), profile.getProfileId(), provider.getAuthorities());
        }
        else {
            return forbidden("Unauthorized profile");
        }

        return generateToken(credentials);
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshToken() {
        return generateToken(getCredentials());
    }

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/login/reset/metadata")
    public FormMetadata getResetMetadata() {
        return new FormMetadata().setRules(validationService.generateRules(ResetPassword.class));
    }

/* --------------------------------------------------------------------------------------------- */

    @PostMapping("/login/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword request)
    {
        // TODO
        return ok();
    }

/* --------------------------------------------------------------------------------------------- */

    private ResponseEntity<?> generateToken(UserCredentials credentials)
    {
        AuthResponse response = new AuthResponse();

        response.setToken(jwtProvider.generateToken(credentials.getUserId(), credentials.getProfileName(),
                credentials.getProfileId()));

        response.setName(credentials.getPersonName());

        if (credentials.isAdmin()) 
        {
            List<AccessBadge> badges = new ArrayList<AccessBadge>(1);

            badges.add(new AccessBadge("admin", null, null));
            response.setProfiles(badges);
        }
        else {
            response.setProfiles(usuarioService.getAccessBadges(credentials.getPersonId()));
        }

        return ok(response);
    }

/* --------------------------------------------------------------------------------------------- */

}
