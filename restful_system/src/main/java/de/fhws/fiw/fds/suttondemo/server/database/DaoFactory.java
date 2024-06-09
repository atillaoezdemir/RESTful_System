/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.suttondemo.server.database;


import de.fhws.fiw.fds.suttondemo.server.database.inmemory.ModuleStorage;
import de.fhws.fiw.fds.suttondemo.server.database.inmemory.UniversityModuleStorage;
import de.fhws.fiw.fds.suttondemo.server.database.inmemory.UniversityStorage;

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final UniversityDao universityDao;

    private final ModuleDao moduleDao;

    private final UniversityModuleDao universityModuleDao;

    private DaoFactory(UniversityDao universityDao, ModuleDao moduleDao, UniversityModuleDao universityModuleDao) {
        this.universityDao = universityDao;
        this.moduleDao = moduleDao;
        this.universityModuleDao = universityModuleDao;
    }

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public UniversityDao getUniversityDao() {
        return universityDao;
    }

    public UniversityModuleDao getUniversityModuleDao() {
        return universityModuleDao;
    }
}
