/*
 * DHBW Engineering Stuttgart e.V.
 * All rights reserved
 * 2016
 */
package de.dhbwengineering.esleektestingdb.threads;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import de.dhbwengineering.esleektestingdb.config.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Uploads a file via SFTP
 *
 * @author Leon
 */
public class UploadSFTP extends Thread {

    private String origin;
    private String destination;
    private String finalName;

    public UploadSFTP(String origin, String destination, String finalName) {
        this.origin = origin;
        this.destination = destination;
        this.finalName = finalName;
    }

    public void run() {

        String fileName = destination;
        String SFTPHOST = Configuration.sFTPServer;
        int SFTPPORT = 22;
        String SFTPUSER = Configuration.sFTPUsername;
        String SFTPPASS = Configuration.sFTPPassword;
        String SFTPWORKINGDIR = origin;

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println(SFTPWORKINGDIR);
        System.out.println("preparing the host information for sftp.");
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            File f = new File(fileName);
            FileOutputStream loadedFile = new FileOutputStream(new File(destination));
            channelSftp.get(finalName, loadedFile);
            System.out.println("File transfered successfully to host.");
        } catch (Exception ex) {
            System.out.println("Exception found while tranfer the response.");
        } finally {

            channelSftp.exit();
            System.out.println("sftp Channel exited.");
            channel.disconnect();
            System.out.println("Channel disconnected.");
            session.disconnect();
            System.out.println("Host Session disconnected.");
        }
    }
}
