# Git-Flow Maven Plugin

[![License](https://img.shields.io/badge/License-Apache%20License%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

This is a fork from [original plugin](https://github.com/aleksandr-m/gitflow-maven-plugin)

The advantages over the original:
  - Fix issue `releaseBranchPrefix`: you wont get error `Release branch already exists` anymore
  - Fix issue `keepBranch=true`: you wont get error `More than one release branch exists` anymore
  - Push `releaseBranch` to remote, require `keepBranch=true`
  - Merge [additional_goals](https://github.com/aleksandr-m/gitflow-maven-plugin/tree/feature/additional_goals) feature
  
Sample of usage:
```xml
<plugin>
    <groupId>com.github.phuonghuynh</groupId>
    <artifactId>gitflow-maven-plugin</artifactId>
    <version>1.6.3</version>
    <configuration>
        <gitFlowConfig>
            <developmentBranch>master</developmentBranch>
            <releaseBranchPrefix>release-</releaseBranchPrefix>
            <versionTagPrefix>v</versionTagPrefix>
        </gitFlowConfig>
        <releaseRebase>true</releaseRebase>
        <keepBranch>true</keepBranch>
        <releaseFinish>
            <preProductionMergeGoals>clean install deploy</preProductionMergeGoals>
        </releaseFinish>
    </configuration>
</plugin>
```