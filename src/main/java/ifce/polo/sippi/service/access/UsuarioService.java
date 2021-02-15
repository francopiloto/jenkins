package ifce.polo.sippi.service.access;

import static ifce.polo.sippi.service.ContextProvider.getBeans;
import static ifce.polo.sippi.service.ContextProvider.getCredentials;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ifce.polo.sippi.dto.access.AccessBadge;
import ifce.polo.sippi.model.access.Usuario;
import ifce.polo.sippi.repository.access.UsuarioRepository;
import ifce.polo.sippi.security.UserCredentials;
import ifce.polo.sippi.util.UID;

@Service
public class UsuarioService implements UserDetailsService
{
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

/* --------------------------------------------------------------------------------------------- */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Usuario usuario = usuarioRepository.findByNome(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }

        return new UserCredentials(usuario);
    }

/* --------------------------------------------------------------------------------------------- */

    public UserCredentials loadUserById(Long id)
    {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            throw new UsernameNotFoundException("UserId '" + id + "' not found.");
        }

        return new UserCredentials(usuario);
    }

/* --------------------------------------------------------------------------------------------- */

    public List<AccessBadge> getAccessBadges(Long personId)
    {
        Collection<AccessProvider> beans = getBeans(AccessProvider.class);
        List<AccessBadge> badges = new ArrayList<AccessBadge>();

        List<AccessProvider> providers = beans instanceof List
                ? (List<AccessProvider>) beans
                : new ArrayList<AccessProvider>(beans);

        Collections.sort(providers, new Comparator<AccessProvider>()
        {
            public int compare(AccessProvider o1, AccessProvider o2) {
                return Integer.compare(o1.getPriority(), o2.getPriority());
            }
        });

        for (AccessProvider provider : providers) {
            badges.addAll(provider.getAccessBadges(new UID(personId, "pessoa")));
        }

        return badges;
    }

/* --------------------------------------------------------------------------------------------- */

    public AccessProvider getAccessProvider(String profileName)
    {
        for (AccessProvider provider : getBeans(AccessProvider.class))
        {
            if (provider.getManagedProfileName().contentEquals(profileName)) {
                return provider;
            }
        }

        return null;
    }

/* --------------------------------------------------------------------------------------------- */

    public boolean verifyPassword(String password)
    {
        Long id = getCredentials().getUserId();

        if (id == null) {
            return false;
        }

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return false;
        }

        return passwordEncoder.matches(password, usuario.getSenha());
    }

/* --------------------------------------------------------------------------------------------- */

    public void changePassword(String password)
    {
		Usuario usuario = usuarioRepository.findById(getCredentials().getUserId()).orElse(null);
		usuario.setSenha(passwordEncoder.encode(password));

		usuario = usuarioRepository.save(usuario);
		getCredentials().update(usuario);
    }

/* --------------------------------------------------------------------------------------------- */

}
