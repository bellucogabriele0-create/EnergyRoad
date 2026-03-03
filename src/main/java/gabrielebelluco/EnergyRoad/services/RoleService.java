package gabrielebelluco.EnergyRoad.services;

import gabrielebelluco.EnergyRoad.entities.Role;
import gabrielebelluco.EnergyRoad.enums.RoleType;
import gabrielebelluco.EnergyRoad.exceptions.NotFoundException;
import gabrielebelluco.EnergyRoad.payloads.RoleDTO;
import gabrielebelluco.EnergyRoad.repositories.RoleRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<Role> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Role findById(UUID ruoloId) {
        return roleRepository.findById(ruoloId).orElseThrow(()
                -> new NotFoundException("Ruolo non trovato con id: " + ruoloId));
    }

    public Role saveRuolo(RoleDTO payload) {
        if (roleRepository.existsByRoleType(payload.getRoleType()))
            throw new IllegalArgumentException("Il ruolo " + payload.getRoleType() + " esiste già");
        Role savedRole = new Role();
        savedRole.setRoleType(payload.getRoleType());
        System.out.println("è stato salvato con successo ruolo di: " + payload.getRoleType());
        return roleRepository.save(savedRole);
    }

    public Role findByIdAndUpdate(UUID ruoloId, RoleDTO payload) {
        Role found = this.findById(ruoloId);
        found.setRoleType(payload.getRoleType());
        System.out.println("è stato modificato con successo il ruolo con id :" + ruoloId);
        return roleRepository.save(found);
    }

    public void findByIdAndDelete(UUID ruoloId) {
        Role found = this.findById(ruoloId);
        roleRepository.delete(found);
    }

    @Transactional
    public Role addRole(RoleDTO payload) throws BadRequestException {
        // Verifica che il ruolo non esista già
        if (roleRepository.existsByRoleType(payload.getRoleType())) {
            throw new BadRequestException("è già esiste il ruolo: " + payload.getRoleType());
        }
        Role newRuolo = new Role(payload.getRoleType());
        Role saved = this.roleRepository.save(newRuolo);
        System.out.println("Ruolo creato: " + saved.getRoleType());
        return saved;
    }

    public boolean existsByRoleType(RoleType roleType) {
        return roleRepository.existsByRoleType(roleType);
    }
}
