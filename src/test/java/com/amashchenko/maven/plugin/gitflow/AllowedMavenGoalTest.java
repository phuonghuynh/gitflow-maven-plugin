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

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AllowedMavenGoalTest {
    private final String goals;
    private final boolean expected;

    public AllowedMavenGoalTest(final String goals, final boolean expected) {
        this.goals = goals;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "clean install", true },
                        { "clean deploy -DsomeArg1=true", true },
                        { "clean deploy -Prelease,deploy", true },
                        { "groupId:artifactId:123.456.7890", true },
                        { "clean & clean", false }, { "clean \\clean", false },
                        { "clean .// clean", false } });
    }

    @Test
    public void testIsAllowedMavenGoal() throws Exception {
        Assert.assertEquals(expected,
                ConfigurationValidator.isAllowedMavenGoal(goals));
    }
}
