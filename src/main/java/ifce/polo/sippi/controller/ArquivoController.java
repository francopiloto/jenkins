package ifce.polo.sippi.controller;

import static ifce.polo.sippi.service.ContextProvider.getCredentials;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ifce.polo.sippi.model.Arquivo;
import ifce.polo.sippi.repository.ArquivoRepository;
import ifce.polo.sippi.security.JwtTokenProvider;
import ifce.polo.sippi.security.SecurityConstants;
import ifce.polo.sippi.security.UserCredentials;
import ifce.polo.sippi.service.access.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class ArquivoController extends AbstractController
{
    @Autowired private ArquivoRepository arquivoRepository;
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired private UsuarioService usuarioService;

/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/v1/arquivo/{id}")
    public ResponseEntity<?> getToken(@PathVariable Long id)
    {
        UserCredentials credentials = getCredentials();
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("fid", id);
        
		String token = jwtProvider.generateToken(credentials.getUserId(), 20000, claims);
		return ok(token);
    }
    
/* --------------------------------------------------------------------------------------------- */

    @GetMapping("/v1/download/{token}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String token)
    {
        try
        {
        	Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = Long.parseLong(claims.get("id").toString());
            Long fileId = Long.parseLong(claims.get("fid").toString());
            
            UserCredentials credentials = usuarioService.loadUserById(userId);
            
            if (credentials == null) {
            	return null;
            }
            
            Arquivo arquivo = arquivoRepository.findById(fileId).orElse(null);
            byte[] data = arquivo.getDados();

            ByteArrayResource resource = new ByteArrayResource(data);

            MediaType type;

            switch (arquivo.getNome().toLowerCase().substring(arquivo.getNome().lastIndexOf('.') + 1))
            {
                case "pdf":
                    type = MediaType.APPLICATION_PDF;
                    break;

                case "jpg":
                case "jpeg":
                    type = MediaType.IMAGE_JPEG;
                    break;

                case "png":
                    type = MediaType.IMAGE_PNG;
                    break;

                case "txt":
                    type = MediaType.TEXT_PLAIN;
                    break;

                default:
                    type = MediaType.APPLICATION_OCTET_STREAM;
                    break;
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + arquivo.getNome())
                    .contentType(type)
                    .contentLength(data.length)
                    .body(resource);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

/* --------------------------------------------------------------------------------------------- */

}
