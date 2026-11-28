package com.app.service;

import com.app.dto.RequestLinkEntity;
import com.app.dto.ResponseLinkEntity;
import com.app.entity.LinkEntity;
import com.app.exception.InvalidLinkException;
import com.app.exception.LinkAlreadyRegisteredException;
import com.app.repository.LinkEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        LinkEntity entity = linkEntityRepository.findByUserLink(requestLinkEntity.userLink()).orElseThrow(() -> new LinkAlreadyRegisteredException("Link Already Registered"));

        String shortenedLink = UUID.randomUUID().toString();

        entity.setUserLink(requestLinkEntity.userLink());
        entity.setShortenedLink(shortenedLink);

        linkEntityRepository.save(entity);

        return new ResponseLinkEntity(shortenedLink);
    }

    public String redirectBrowser(String shortenedLink) {
        LinkEntity linkEntity = linkEntityRepository.findByShortenedLink(shortenedLink).orElseThrow(() -> new InvalidLinkException("Invalid Link"));

        return linkEntity.getUserLink();
    }
}
