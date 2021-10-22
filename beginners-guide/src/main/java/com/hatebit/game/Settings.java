package com.hatebit.game;

import com.jme3.system.AppSettings;

public class Settings {

    public static AppSettings defaultSettings() {
        final AppSettings settings = new AppSettings(true);
        settings.setResolution(640, 480);
        settings.setDepthBits(24);
        settings.setFullscreen(false);
        settings.setTitle("monkey business");
        settings.setSettingsDialogImage("logo.png");
        return settings;
    }
}
