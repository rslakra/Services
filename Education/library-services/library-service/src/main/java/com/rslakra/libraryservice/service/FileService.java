package com.rslakra.libraryservice.service;

import com.rslakra.libraryservice.persistence.entity.File;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface FileService extends BaseService<File> {

    /**
     * Returns the list of files by name.
     *
     * @param name
     * @return
     */
    public File getByName(String name);

    /**
     * Returns the list of files by contents.
     *
     * @param contents
     * @return
     */
    public List<File> getByContents(String contents);

}
