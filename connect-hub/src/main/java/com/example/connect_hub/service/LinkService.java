package com.example.connect_hub.service;


import com.example.connect_hub.model.Link;
import com.example.connect_hub.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public Link createLink(Link link) {
        return linkRepository.save(link);
    }

    public List<Link> getLinksByUserId(String userId) {
        return linkRepository.findByUserId(userId);
    }

    public void deleteLink(String linkId) {
        linkRepository.deleteById(linkId);
    }

    public Link incrementClicks(String linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(() -> new RuntimeException("Link not found"));
        link.setClicks(link.getClicks() + 1);
        return linkRepository.save(link);
    }
    // Add this method to the LinkService class
    public Link incrementLinkClicks(String linkId) {
        Link link = linkRepository.findById(linkId)
                .orElseThrow(() -> new RuntimeException("Link not found"));
        link.setClicks(link.getClicks() + 1);
        return linkRepository.save(link);
    }
    public Link getLinkById(String id) {
        return linkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Link not found"));
    }
}