package com.Minor2CCh.elytraslot_compat.config;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import org.spongepowered.include.com.google.gson.Gson;
import org.spongepowered.include.com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ECConfigLoader {
    private static final File DIR = ElytraslotCompat.PLATFORM.getConfigPath().toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = ElytraslotCompat.MOD_ID+".json";
    private static final Path CONFIG_PATH = Path.of(new File(DIR,FILENAME).getPath());
    private static ECConfig modConfig;
    public static void load(){
        System.out.println(CONFIG_PATH);
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                modConfig = GSON.fromJson(reader, ECConfig.class);
            } catch (IOException e) {
                System.err.println("Failed to load config: " + e.getMessage());
                modConfig = new ECConfig();
            }
            try{
                modConfig.fillDefaults();
            } catch (Exception e) {
                System.err.println("Failed to load config: " + e.getMessage());
                modConfig = new ECConfig();
            }
            modConfig.fillDefaults(); // ここで新フィールド補完
        } else {
            modConfig = new ECConfig();
        }
        save(); // 初回生成
    }
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(modConfig, writer);
            }
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
    public static ECConfig getConfig() {
        return modConfig;
    }
}
