package io.nativeblocks.nativeblocks.ecommerce.data

object NativeblocksRoutes {

    // Global parameter keys
    const val CAMPAIGN_ID_PARAM = "campaignId"
    const val USER_TYPE_PARAM = "userType"
    const val SOURCE_PARAM = "source"
    const val PLATFORM_PARAM = "platform"
    const val SESSION_TIMESTAMP_PARAM = "sessionTimestamp"

    // User type values
    const val USER_TYPE_ORGANIC = "organic"
    const val USER_TYPE_DEEPLINK = "deeplink"

    // Source values
    const val SOURCE_ORGANIC = "organic"
    const val SOURCE_DEEPLINK = "deeplink"

    // Platform value
    const val PLATFORM_ANDROID = "android"

    fun getCampaignParameters(campaignId: String?): Array<Pair<String, String>> {
        val params = mutableListOf<Pair<String, String>>()
        campaignId?.let {
            params.add(CAMPAIGN_ID_PARAM to it)
        }
        params.add(SOURCE_PARAM to if (campaignId != null) SOURCE_DEEPLINK else SOURCE_ORGANIC)
        params.add(PLATFORM_PARAM to PLATFORM_ANDROID)
        params.add(SESSION_TIMESTAMP_PARAM to System.currentTimeMillis().toString())

        return params.toTypedArray()
    }

    fun getUserSegmentParameters(
        userType: String = USER_TYPE_ORGANIC,
        userId: String? = null,
        location: String? = null,
        previousPurchases: Int = 0,
        appOpenCount: Int = 1
    ): Array<Pair<String, String>> {
        val params = mutableListOf<Pair<String, String>>()

        params.add(USER_TYPE_PARAM to userType)
        userId?.let { params.add("userId" to it) }
        location?.let { params.add("location" to it) }
        params.add("previousPurchases" to previousPurchases.toString())
        params.add("appOpenCount" to appOpenCount.toString())
        params.add("sessionType" to if (previousPurchases > 0) "returning" else "new")

        return params.toTypedArray()
    }
}