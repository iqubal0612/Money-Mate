package com.imam.moneymate.service;

import com.imam.moneymate.dto.AuthDTO;
import com.imam.moneymate.dto.ProfileDTO;
import com.imam.moneymate.entity.Profile;

import java.util.Map;

public interface ProfileService {

    public ProfileDTO registerProfile(ProfileDTO profileDTO);

    public Boolean activateToken(String activationToken);

    public boolean isAccountActive(String email);

    public Profile getcurrentProfile();

    public ProfileDTO getPublicProfile(String email);

    Map<String, Object> authenticateAndGenerateToken(AuthDTO authDTO);
}
