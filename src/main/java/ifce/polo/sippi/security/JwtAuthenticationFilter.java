package ifce.polo.sippi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import ifce.polo.sippi.service.access.AccessProvider;
import ifce.polo.sippi.service.access.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired private UsuarioService usuarioService;

/* --------------------------------------------------------------------------------------------- */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        String token = request.getHeader("Authorization");

        if (token != null)
        {
            try
            {
                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(token)
                        .getBody();

                Long userId = Long.parseLong(claims.get("id").toString());
                UserCredentials credentials = usuarioService.loadUserById(userId);

                if (credentials != null)
                {
                    Object profile = claims.get("profile");

                    if (profile != null)
                    {
                        String profileName = profile.toString();
                        Object pid = claims.get("pid");

                        Long profileId = pid != null ? Long.parseLong(pid.toString()) : null;

                        AccessProvider provider = usuarioService.getAccessProvider(profileName);
                        credentials.setProfile(profileName, profileId, provider.getAuthorities());
                    }

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(credentials,
                            null, credentials.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            catch (Exception e) {}
        }

        filterChain.doFilter(request, response);
    }

/* --------------------------------------------------------------------------------------------- */

}
