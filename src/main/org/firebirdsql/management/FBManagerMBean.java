/*
 * Firebird Open Source JavaEE Connector - JDBC Driver
 *
 * Distributable under LGPL license.
 * You may obtain a copy of the License at http://www.gnu.org/copyleft/lgpl.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * LGPL License for more details.
 *
 * This file was created by members of the firebird development team.
 * All individual contributions remain the Copyright (C) of those
 * individuals.  Contributors to this file are either listed here or
 * can be obtained from a source control history command.
 *
 * All rights reserved.
 */
package org.firebirdsql.management;

/**
 * Describe interface <code>FBManagerMBean</code> here.
 *
 * @author <a href="mailto:d_jencks@users.sourceforge.net">David Jencks</a>
 * @version 1.0
 */
public interface FBManagerMBean {

    void start() throws Exception;

    void stop() throws Exception;

    String getName();

    String getState();

    void setServer(String host);

    String getServer();

    void setPort(int port);

    int getPort();

    /**
     * mbean get-set pair for field fileName
     * Get the value of fileName
     *
     * @return value of fileName
     */
    String getFileName();

    /**
     * Set the value of fileName
     *
     * @param fileName
     *         Value to assign to fileName
     */
    void setFileName(final String fileName);

    /**
     * mbean get-set pair for field userName
     * Get the value of userName
     *
     * @return value of userName
     */
    String getUserName();

    /**
     * Set the value of userName
     *
     * @param userName
     *         Value to assign to userName
     */
    void setUserName(final String userName);

    /**
     * mbean get-set pair for field password
     * Get the value of password
     *
     * @return value of password
     */
    String getPassword();

    /**
     * Set the value of password
     *
     * @param password
     *         Value to assign to password
     */
    void setPassword(final String password);

    /**
     * mbean get-set pair for field createOnStart
     * Get the value of createOnStart
     *
     * @return value of createOnStart
     */
    boolean isCreateOnStart();

    /**
     * Set the value of createOnStart
     *
     * @param createOnStart
     *         Value to assign to createOnStart
     */
    void setCreateOnStart(final boolean createOnStart);

    /**
     * mbean get-set pair for field dropOnStop
     * Get the value of dropOnStop
     *
     * @return value of dropOnStop
     */
    boolean isDropOnStop();

    /**
     * Set the value of dropOnStop
     *
     * @param dropOnStop
     *         Value to assign to dropOnStop
     */
    void setDropOnStop(final boolean dropOnStop);

    /**
     * Get the ForceCreate value.
     *
     * @return the ForceCreate value.
     */
    boolean isForceCreate();

    /**
     * Set the ForceCreate value.
     *
     * @param forceCreate
     *         The new ForceCreate value.
     */
    void setForceCreate(boolean forceCreate);

    void createDatabase(String filename, String user, String password) throws Exception;

    void dropDatabase(String fileName, String user, String password) throws Exception;

    boolean isDatabaseExists(String fileName, String user, String password) throws Exception;

}
