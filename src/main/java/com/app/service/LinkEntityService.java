package com.app.service;

import com.app.dto.RequestLinkEntity;
import com.app.dto.ResponseLinkEntity;
import com.app.entity.LinkEntity;
import com.app.exception.InvalidLinkException;
import com.app.exception.LinkAlreadyRegisteredException;
import com.app.repository.LinkEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LinkEntityService {
    private final LinkEntityRepository linkEntityRepository;
    private final UUID uuid;

    @Autowired
    public LinkEntityService(LinkEntityRepository linkEntityRepository) {
        this.linkEntityRepository = linkEntityRepository;
        this.uuid = UUID.randomUUID();
    }

    public ResponseLinkEntity createLinkShortened(RequestLinkEntity requestLinkEntity) {
        if(linkEntityRepository.findByUserLink(requestLinkEntity.userLink()).isPresent()) {
            throw new LinkAlreadyRegisteredException("Link Already Registered");
        }
        String shortenedLink = uuid.toString();

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setUserLink(requestLinkEntity.userLink());
        linkEntity.setShortenedLink(shortenedLink);

        linkEntityRepository.save(linkEntity);

        return new ResponseLinkEntity(shortenedLink);
    }

    public String redirectBrowser(String shortenedLink) {
        if(linkEntityRepository.findByShortenedLink(shortenedLink).isEmpty()) {
            throw new InvalidLinkException("Invalid Link");
        }

        Optional<LinkEntity> linkEntity = linkEntityRepository.findByShortenedLink(shortenedLink);

        return linkEntity.get().getUserLink();
    }
}
