package com.ahrokholska.gitHubUsers.data.remote.repositories

import com.google.gson.annotations.SerializedName

data class RepositoryDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("owner") val owner: Owner?,
    @SerializedName("private") val private: Boolean?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean?,
    @SerializedName("url") val url: String?,
    @SerializedName("archive_url") val archiveUrl: String?,
    @SerializedName("assignees_url") val assigneesUrl: String?,
    @SerializedName("blobs_url") val blobsUrl: String?,
    @SerializedName("branches_url") val branchesUrl: String?,
    @SerializedName("collaborators_url") val collaboratorsUrl: String?,
    @SerializedName("comments_url") val commentsUrl: String?,
    @SerializedName("commits_url") val commitsUrl: String?,
    @SerializedName("compare_url") val compareUrl: String?,
    @SerializedName("contents_url") val contentsUrl: String?,
    @SerializedName("contributors_url") val contributorsUrl: String?,
    @SerializedName("deployments_url") val deploymentsUrl: String?,
    @SerializedName("downloads_url") val downloadsUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("forks_url") val forksUrl: String?,
    @SerializedName("git_commits_url") val gitCommitsUrl: String?,
    @SerializedName("git_refs_url") val gitRefsUrl: String?,
    @SerializedName("git_tags_url") val gitTagsUrl: String?,
    @SerializedName("git_url") val gitUrl: String?,
    @SerializedName("issue_comment_url") val issueCommentUrl: String?,
    @SerializedName("issue_events_url") val issueEventsUrl: String?,
    @SerializedName("issues_url") val issuesUrl: String?,
    @SerializedName("keys_url") val keysUrl: String?,
    @SerializedName("labels_url") val labelsUrl: String?,
    @SerializedName("languages_url") val languagesUrl: String?,
    @SerializedName("merges_url") val mergesUrl: String?,
    @SerializedName("milestones_url") val milestonesUrl: String?,
    @SerializedName("notifications_url") val notificationsUrl: String?,
    @SerializedName("pulls_url") val pullsUrl: String?,
    @SerializedName("releases_url") val releasesUrl: String?,
    @SerializedName("ssh_url") val sshUrl: String?,
    @SerializedName("stargazers_url") val stargazersUrl: String?,
    @SerializedName("statuses_url") val statusesUrl: String?,
    @SerializedName("subscribers_url") val subscribersUrl: String?,
    @SerializedName("subscription_url") val subscriptionUrl: String?,
    @SerializedName("tags_url") val tagsUrl: String?,
    @SerializedName("teams_url") val teamsUrl: String?,
    @SerializedName("trees_url") val treesUrl: String?,
    @SerializedName("clone_url") val cloneUrl: String?,
    @SerializedName("mirror_url") val mirrorUrl: String?,
    @SerializedName("hooks_url") val hooksUrl: String?,
    @SerializedName("svn_url") val svnUrl: String?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("forks_count") val forksCount: Int?,
    @SerializedName("stargazers_count") val stargazersCount: Int?,
    @SerializedName("watchers_count") val watchersCount: Int?,
    @SerializedName("size") val size: Int?,
    @SerializedName("default_branch") val defaultBranch: String?,
    @SerializedName("open_issues_count") val openIssuesCount: Int?,
    @SerializedName("is_template") val isTemplate: Boolean?,
    @SerializedName("topics") val topics: ArrayList<String> = arrayListOf(),
    @SerializedName("has_issues") val hasIssues: Boolean?,
    @SerializedName("has_projects") val hasProjects: Boolean?,
    @SerializedName("has_wiki") val hasWiki: Boolean?,
    @SerializedName("has_pages") val hasPages: Boolean?,
    @SerializedName("has_downloads") val hasDownloads: Boolean?,
    @SerializedName("has_discussions") val hasDiscussions: Boolean?,
    @SerializedName("archived") val archived: Boolean?,
    @SerializedName("disabled") val disabled: Boolean?,
    @SerializedName("visibility") val visibility: String?,
    @SerializedName("pushed_at") val pushedAt: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("permissions") val permissions: Permissions?,
    @SerializedName("security_and_analysis") val securityAndAnalysis: SecurityAndAnalysis?
)

data class Owner(
    @SerializedName("login") val login: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("gravatar_id") val gravatarId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("followers_url") val followersUrl: String?,
    @SerializedName("following_url") val followingUrl: String?,
    @SerializedName("gists_url") val gistsUrl: String?,
    @SerializedName("starred_url") val starredUrl: String?,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String?,
    @SerializedName("organizations_url") val organizationsUrl: String?,
    @SerializedName("repos_url") val reposUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("received_events_url") val receivedEventsUrl: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("site_admin") val siteAdmin: Boolean?
)

data class Permissions(
    @SerializedName("admin") val admin: Boolean?,
    @SerializedName("push") val push: Boolean?,
    @SerializedName("pull") val pull: Boolean?
)

data class AdvancedSecurity(@SerializedName("status") val status: String?)

data class SecretScanning(@SerializedName("status") val status: String?)

data class SecretScanningPushProtection(@SerializedName("status") val status: String?)

data class SecurityAndAnalysis(
    @SerializedName("advanced_security") val advancedSecurity: AdvancedSecurity?,
    @SerializedName("secret_scanning") val secretScanning: SecretScanning?,
    @SerializedName("secret_scanning_push_protection") val secretScanningPushProtection: SecretScanningPushProtection?
)