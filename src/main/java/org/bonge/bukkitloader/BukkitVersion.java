package org.bonge.bukkitloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class BukkitVersion {

    private BukkitType type;
    private String download;
    private long bytes;
    private int[] targetVersion;

    public BukkitVersion(BukkitType type, int major, int minor, int version){
        this(null, 0, type, major, minor, version);
    }

    public BukkitVersion(String download, long bytes, BukkitType type, int major, int minor, int version){
        this.targetVersion = new int[]{major, minor, version};
        this.type = type;
        this.download = download;
        this.bytes = bytes;
    }

    public Optional<URL> getDownload() throws MalformedURLException {
        if(download == null){
            return Optional.empty();
        }
        return Optional.of(new URL(this.download));
    }

    public Optional<String> getDownloadURL(){
        if(this.download == null){
            return Optional.empty();
        }
        return Optional.of(this.download);
    }

    public BukkitType getType(){
        return this.type;
    }

    public int[] getTargetVersion(){
        return this.targetVersion;
    }

    public long getSizeInBytes(){
        return this.bytes;
    }
}
