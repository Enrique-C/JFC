/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.utility;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.io.File;

/**
 * Generates link of zip file.
 *
 * @version 0.1 03 Jan 2020
 *
 * @author Juan Martinez
 */
public class LinkGenerator {

    /**
     * Generates link of zip file.
     * @param filePath value.
     * @param request value of client request.
     * @return link for downloading zip file.
     */
    public String linkGenerator(String filePath, HttpServletRequest request){

        // File instance.
        File file = new File(filePath);

        // file name value.
        String fileZipName = file.getName();

        // fullURL value.
        String fullURL = request.getRequestURL().toString();

        // URI value.
        UriComponents uriInfo = UriComponentsBuilder.fromUriString(fullURL).build();

        // String of URL.
        String fileDownloadUri = UriComponentsBuilder.newInstance().scheme(uriInfo.getScheme()).
                host(uriInfo.getHost()).port(uriInfo.getPort()).path("/api/download/").path(fileZipName).build().toString();
        return fileDownloadUri;
    }
}
