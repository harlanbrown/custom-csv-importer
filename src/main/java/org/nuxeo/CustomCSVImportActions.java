/*
 * (C) Copyright 2012 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Thomas Roger
 */

package org.nuxeo.sample;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.csv.core.CSVImporter;
import org.nuxeo.ecm.csv.core.CSVImporterOptions;
import org.nuxeo.ecm.csv.jsf.CSVImportActions;
import org.nuxeo.runtime.api.Framework;

/**
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 * @since 5.7
 */
@Scope(ScopeType.CONVERSATION)
@Name("customCsvImportActions")
@Install(precedence = Install.FRAMEWORK)
public class CustomCSVImportActions extends CSVImportActions {

    private static final long serialVersionUID = 1L;

    public void importCSVFile() {
        if (csvFile != null) {
            CSVImporterOptions options = new CSVImporterOptions.Builder().sendEmail(notifyUserByEmail)
                                                                         .importMode(getImportMode())
                                                                         .dateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                                                                         .build();
            CSVImporter csvImporter = Framework.getService(CSVImporter.class);
            csvImportId = csvImporter.launchImport(documentManager,
                    navigationContext.getCurrentDocument().getPathAsString(), csvFile, csvFileName, options);
        }
    }
}
