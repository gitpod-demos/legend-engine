// Copyright 2023 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.language.snowflakeApp.deployment;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.finos.legend.engine.functionActivator.deployment.FunctionActivatorDeploymentContent;
import org.finos.legend.engine.protocol.pure.v1.model.context.AlloySDLC;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.store.relational.model.Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SnowflakeAppContent extends FunctionActivatorDeploymentContent
{
    public MutableList<String> sqlExpressions = Lists.mutable.empty();

    public String applicationName;
    public String description;
    public List<String> owners;
    public String groupId;
    public String artifactId;
    public String version;
    public String creationTime;

    public SnowflakeAppContent(String applicationName, MutableList<String> sqlExpressions, AlloySDLC sdlc)
    {
        this.applicationName = applicationName;
        this.sqlExpressions = sqlExpressions;
        this.creationTime = convertToValidDate(new Date());
        if (sdlc != null)
        {
            this.groupId = sdlc.groupId;
            this.artifactId = sdlc.artifactId;
            this.version = sdlc.version;
        }
    }

    public SnowflakeAppContent(String applicationName, MutableList<String> sqlExpressions, String description, List<String> owners, AlloySDLC sdlc)
    {
        this(applicationName, sqlExpressions,sdlc);
        this.description = description;
        this.owners = owners;

    }

    public static String convertToValidDate(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public String getVersionInfo()
    {
        if (this.version != null)
        {
            return groupId + ":" + this.artifactId + ":" + this.version;
        }
        return "";
    }
}

