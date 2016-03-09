/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.appserver;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.appserver.configuration.listeners.Utils;
import org.wso2.appserver.configuration.server.AppServerConfiguration;
import org.wso2.appserver.exceptions.ApplicationServerConfigurationException;
import org.wso2.appserver.exceptions.ApplicationServerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class defines unit-tests for XML utilities.
 *
 * @since 6.0.0
 */
public class UtilsTest {
    @Test(expectedExceptions = { ApplicationServerConfigurationException.class })
    public void loadObjectFromNonExistentSchemaAsPath() throws ApplicationServerConfigurationException {
        Path xmlSource = Paths.
                get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER, TestConstants.SAMPLE_XML_FILE);
        Path xmlSchema = Paths.get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER,
                TestConstants.NON_EXISTENT_XSD_FILE);
        Utils.getUnmarshalledObject(xmlSource, xmlSchema, AppServerConfiguration.class);
    }

    @Test(expectedExceptions = { ApplicationServerConfigurationException.class })
    public void invalidSchemaTest() throws ApplicationServerException {
        Path xmlSchema = Paths.
                get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER, TestConstants.INVALID_XSD_FILE);
        Utils.getXMLUnmarshaller(xmlSchema, AppServerConfiguration.class);
    }

    @Test(expectedExceptions = { ApplicationServerConfigurationException.class })
    public void loadObjectFromInvalidFileTest() throws ApplicationServerException {
        Path xmlSource = Paths.
                get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER, TestConstants.INVALID_XML_FILE);
        Path xmlSchema = Paths.
                get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER, TestConstants.SAMPLE_XSD_FILE);
        Utils.getUnmarshalledObject(xmlSource, xmlSchema, AppServerConfiguration.class);
    }

    @Test(expectedExceptions = { ApplicationServerConfigurationException.class })
    public void loadObjectFromInvalidInputStreamTest() throws ApplicationServerException {
        Path xmlSource = Paths.
                get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER, TestConstants.INVALID_XML_FILE);
        Path xmlSchema = Paths.
                get(TestConstants.BUILD_DIRECTORY, TestConstants.TEST_RESOURCE_FOLDER, TestConstants.SAMPLE_XSD_FILE);
        try {
            Utils.getUnmarshalledObject(Files.newInputStream(xmlSource), xmlSchema, AppServerConfiguration.class);
        } catch (IOException e) {
            Assert.fail();
        }
    }
}