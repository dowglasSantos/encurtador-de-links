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

    @Autowired
    public LinkEntityService(LinkEntityRepository linkEntityRepository) {
        this.linkEntityRepository = linkEntityRepository;
    }

    public ResponseLinkEntity createLinkShortened(RequestLinkEntity requestLinkEntity) {
        if(linkEntityRepository.findByUserLink(requestLinkEntity.userLink()).isPresent()) {
            throw new LinkAlreadyRegisteredException("Link Already Registered");
        }
        String shortenedLink = UUID.randomUUID().toString();

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setUserLink(requestLinkEntity.userLink());
        linkEntity.setShortenedLink(shortenedLink);

        linkEntityRepository.save(linkEntity);

        return new ResponseLinkEntity(shortenedLink);
    }

    public String redirectBrowser(String shortenedLink) {
        Optional<LinkEntity> linkEntity = linkEntityRepository.findByShortenedLink(shortenedLink);

        if(linkEntity.isEmpty()) {
            throw new InvalidLinkException("Invalid Link");
        }

        return linkEntity.get().getUserLink();
    }
}
