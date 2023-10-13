/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 LLC. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein in any form is strictly forbidden, unless permitted by WSO2 expressly.
 * You may not alter or remove any copyright or other notice from copies of this content.
 *
 */

package org.wso2.carbon.identity.local.auth.authenticator.internal;

import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.event.services.IdentityEventService;
import org.wso2.carbon.identity.governance.IdentityGovernanceService;
import org.wso2.carbon.identity.handler.event.account.lock.service.AccountLockService;
import org.wso2.carbon.idp.mgt.IdpManager;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * Encapsulates the data of SMS OTP authenticator.
 */
public class AuthenticatorDataHolder {

    private static volatile AuthenticatorDataHolder authenticatorDataHolder = new AuthenticatorDataHolder();

    private static RealmService realmService;
    private static AccountLockService accountLockService;
    private static IdentityGovernanceService identityGovernanceService;
    private static IdentityEventService identityEventService;
    private static ClaimMetadataManagementService claimMetadataManagementService;
    private static IdpManager idpManager;
    private static ApplicationManagementService applicationManagementService;
    private static ConfigurationManager configurationManager;

    private AuthenticatorDataHolder() {

    }

    public static AuthenticatorDataHolder getInstance() {

        return authenticatorDataHolder;
    }

    /**
     * Get the RealmService.
     *
     * @return RealmService.
     */
    public static RealmService getRealmService() {

        if (realmService == null) {
            throw new RuntimeException("RealmService was not set during the SMS OTP service component startup");
        }
        return realmService;
    }

    /**
     * Set the RealmService.
     *
     * @param realmService RealmService.
     */
    public static void setRealmService(RealmService realmService) {

        AuthenticatorDataHolder.realmService = realmService;
    }

    /**
     * Get Account Lock service.
     *
     * @return Account Lock service.
     */
    public static AccountLockService getAccountLockService() {

        if (accountLockService == null) {
            throw new RuntimeException("AccountLockService was not set during the SMS OTP service component startup");
        }
        return accountLockService;
    }

    /**
     * Set Account Lock service.
     *
     * @param accountLockService Account Lock service.
     */
    public static void setAccountLockService(AccountLockService accountLockService) {

        AuthenticatorDataHolder.accountLockService = accountLockService;
    }

    /**
     * Get Identity Governance service.
     *
     * @return Identity Governance service.
     */
    public static IdentityGovernanceService getIdentityGovernanceService() {

        if (identityGovernanceService == null) {
            throw new RuntimeException("IdentityGovernanceService not available. Component is not started properly.");
        }
        return identityGovernanceService;
    }

    /**
     * Set Identity Governance service.
     *
     * @param identityGovernanceService Identity Governance service.
     */
    public static void setIdentityGovernanceService(IdentityGovernanceService identityGovernanceService) {

        AuthenticatorDataHolder.identityGovernanceService = identityGovernanceService;
    }

    /**
     * Get IdpManager.
     *
     * @return IdpManager.
     */
    public static IdpManager getIdpManager() {

        if (idpManager == null) {
            throw new RuntimeException("IdpManager not available. Component is not started properly.");
        }
        return idpManager;
    }

    /**
     * Set IdpManager.
     *
     * @param idpManager IdpManager.
     */
    public static void setIdpManager(IdpManager idpManager) {

        AuthenticatorDataHolder.idpManager = idpManager;
    }

    /**
     * Get ApplicationManagementService instance.
     *
     * @return ApplicationManagementService instance.
     */
    public static ApplicationManagementService getApplicationManagementService() {

        if (applicationManagementService == null) {
            throw new RuntimeException(
                    "applicationManagementService not available. Component is not started properly.");
        }
        return applicationManagementService;
    }

    /**
     * Set applicationManagementService instance.
     *
     * @param applicationManagementService applicationManagementService instance.
     */
    public static void setApplicationManagementService(ApplicationManagementService applicationManagementService) {

        AuthenticatorDataHolder.applicationManagementService = applicationManagementService;
    }

    /**
     * Get {@link ClaimMetadataManagementService}.
     *
     * @return ClaimMetadataManagementService.
     */
    public static ClaimMetadataManagementService getClaimMetadataManagementService() {

        return AuthenticatorDataHolder.claimMetadataManagementService;
    }

    /**
     * Set {@link ClaimMetadataManagementService}.
     *
     * @param claimMetadataManagementService Instance of {@link ClaimMetadataManagementService}.
     */
    public static void setClaimMetadataManagementService(ClaimMetadataManagementService
                                                                 claimMetadataManagementService) {

        AuthenticatorDataHolder.claimMetadataManagementService = claimMetadataManagementService;
    }

    /**
     * Get IdentityEventService instance.
     *
     * @return IdentityEventService instance.
     */
    public static IdentityEventService getIdentityEventService() {

        return identityEventService;
    }

    /**
     * Set IdentityEventService instance.
     *
     * @param identityEventService IdentityEventService instance.
     */
    public static void setIdentityEventService(IdentityEventService identityEventService) {

        AuthenticatorDataHolder.identityEventService = identityEventService;
    }

    public static void setConfigurationManager(ConfigurationManager configurationManager) {

        AuthenticatorDataHolder.configurationManager = configurationManager;
    }

    public static ConfigurationManager getConfigurationManager() {

        return configurationManager;
    }
}
