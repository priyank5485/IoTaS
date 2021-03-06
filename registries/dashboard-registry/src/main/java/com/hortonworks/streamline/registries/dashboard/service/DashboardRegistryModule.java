package com.hortonworks.streamline.registries.dashboard.service;

import com.hortonworks.streamline.common.ModuleRegistration;
import com.hortonworks.streamline.common.util.FileStorage;
import com.hortonworks.streamline.storage.StorageManager;
import com.hortonworks.streamline.storage.StorageManagerAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardRegistryModule implements ModuleRegistration, StorageManagerAware {
    private Map<String, Object> config;
    private FileStorage fileStorage;
    private StorageManager storageManager;

    @Override
    public void init(Map<String, Object> config, FileStorage fileStorage) {
        this.config = config;
        this.fileStorage = fileStorage;
    }

    @Override
    public List<Object> getResources() {
        List<Object> result = new ArrayList<>();
        DashboardCatalogService dashboardCatalogService = new DashboardCatalogService(storageManager, fileStorage);
        DashboardCatalogResource parserResource = new DashboardCatalogResource(dashboardCatalogService);
        result.add(parserResource);
        return result;
    }

    @Override
    public void setStorageManager(StorageManager storageManager) {
        this.storageManager = storageManager;
    }
}
