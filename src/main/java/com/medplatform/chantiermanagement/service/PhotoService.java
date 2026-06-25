package com.medplatform.chantiermanagement.service;

import com.medplatform.chantiermanagement.dto.PhotoRequest;
import com.medplatform.chantiermanagement.dto.PhotoResponse;
import com.medplatform.chantiermanagement.entity.Photo;
import com.medplatform.chantiermanagement.entity.Rapport;
import com.medplatform.chantiermanagement.exception.PhotoNotFoundException;
import com.medplatform.chantiermanagement.exception.RapportNotFoundException;
import com.medplatform.chantiermanagement.repository.PhotoRepository;
import com.medplatform.chantiermanagement.repository.RapportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final RapportRepository rapportRepository;

    public PhotoResponse create(PhotoRequest request) {
        Photo photo = Photo.builder()
                .urlPhoto(request.getUrlPhoto())
                .dateUpload(request.getDateUpload())
                .rapport(resolveRapport(request.getRapportId()))
                .build();

        return toResponse(photoRepository.save(photo));
    }

    public List<PhotoResponse> getAll() {
        return photoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PhotoResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public PhotoResponse update(Long id, PhotoRequest request) {
        Photo photo = findOrThrow(id);

        photo.setUrlPhoto(request.getUrlPhoto());
        photo.setDateUpload(request.getDateUpload());
        photo.setRapport(resolveRapport(request.getRapportId()));

        return toResponse(photoRepository.save(photo));
    }

    public void delete(Long id) {
        findOrThrow(id);
        photoRepository.deleteById(id);
    }

    private Photo findOrThrow(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException(id));
    }

    private Rapport resolveRapport(Long rapportId) {
        return rapportRepository.findById(rapportId)
                .orElseThrow(() -> new RapportNotFoundException(rapportId));
    }

    private PhotoResponse toResponse(Photo photo) {
        return PhotoResponse.builder()
                .id(photo.getId())
                .urlPhoto(photo.getUrlPhoto())
                .dateUpload(photo.getDateUpload())
                .rapportId(photo.getRapport() != null ? photo.getRapport().getId() : null)
                .build();
    }
}
