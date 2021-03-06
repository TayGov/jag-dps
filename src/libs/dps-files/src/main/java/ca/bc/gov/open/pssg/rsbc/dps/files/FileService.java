package ca.bc.gov.open.pssg.rsbc.dps.files;

import ca.bc.gov.open.pssg.rsbc.dps.sftp.starter.DpsSftpException;

import java.io.InputStream;

/**
 * An interface that expose functionality to manipulate files.
 *
 * @author alexjoybc@github
 *
 */
public interface FileService {

    /**
     * A service that moves files to archive
     * @param fileInfo
     * @throws DpsSftpException
     */
    void moveFilesToArchive(FileInfo fileInfo) throws DpsSftpException;

    /**
     * A service that move files to error
     * @param fileInfo
     * @throws DpsSftpException
     */
    void moveFilesToError(FileInfo fileInfo) throws DpsSftpException;

    InputStream getImageFileContent(FileInfo fileInfo) throws DpsSftpException;

    InputStream getMetadataFileContent(FileInfo fileInfo) throws DpsSftpException;

}
