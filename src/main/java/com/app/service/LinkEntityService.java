package com.app.service;

import com.app.dto.RequestLinkEntity;
import com.app.dto.ResponseLinkEntity;
import com.app.entity.LinkEntity;
import com.app.exception.InvalidLinkException;
import com.app.exception.LinkAlreadyRegisteredException;
import com.app.repository.LinkEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
            throw new LinkAlreadyRegisteredException("Link already registered");
        }

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setUserLink(requestLinkEntity.userLink());
        linkEntity.setShortenedLink(UUID.randomUUID().toString());

        linkEntityRepository.save(linkEntity);
        return new ResponseLinkEntity(linkEntity.getShortenedLink());
    }

    public String redirectBrowser(String shortenedLink) {
        LinkEntity linkEntity = linkEntityRepository.findByShortenedLink(shortenedLink).orElseThrow(() -> new InvalidLinkException("Invalid Link"));

        return linkEntity.getUserLink();
    }
}
