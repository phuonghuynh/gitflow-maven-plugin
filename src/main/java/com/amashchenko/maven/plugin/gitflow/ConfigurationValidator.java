/*
 * Copyright 2014-2017 Aleksandr Mashchenko.
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
 */
package com.amashchenko.maven.plugin.gitflow;

import java.util.regex.Pattern;

public class ConfigurationValidator {
    private static final String LS = System.getProperty("line.separator");

    private static final Pattern MAVEN_ALLOWED_GOALS = Pattern
            .compile("^[a-zA-Z0-9 .,=:_-]+?$");

    public static boolean isAllowedMavenGoal(final String goal) {
        return MAVEN_ALLOWED_GOALS.matcher(goal).matches();
    }

    public static String validateGoalConfig(GoalConfig goalConfig) {
        StringBuilder errors = new StringBuilder();
        if (goalConfig != null) {
            if (goalConfig.getPreProductionMergeGoals() != null
                    && !isAllowedMavenGoal(goalConfig
                            .getPreProductionMergeGoals())) {
                errors.append("preProductionMergeGoals value doesn't match allowed pattern: "
                        + MAVEN_ALLOWED_GOALS);
                errors.append(LS);
            }
            if (goalConfig.getPostProductionMergeGoals() != null
                    && !isAllowedMavenGoal(goalConfig
                            .getPostProductionMergeGoals())) {
                errors.append("postProductionMergeGoals value doesn't match allowed pattern: "
                        + MAVEN_ALLOWED_GOALS);
                errors.append(LS);
            }
        }
        return errors.length() == 0 ? null : errors.toString();
    }

    private ConfigurationValidator() {
    }
}
