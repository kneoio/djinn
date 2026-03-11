package com.semantyca.jesoos.service;

import com.semantyca.jesoos.dto.ProfileDTO;
import com.semantyca.jesoos.repository.ProfileRepository;
import com.semantyca.mixpla.model.Profile;
import io.kneo.core.service.AbstractService;
import io.kneo.core.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class ProfileService extends AbstractService<Profile, ProfileDTO> {

    private final ProfileRepository repository;


    @Inject
    public ProfileService(UserService userService, ProfileRepository repository) {
        super(userService);
        this.repository = repository;
    }

    public Uni<Profile> getById(UUID id) {
        return repository.findById(id);
    }

}