package io.nativeblocks.nativeblocks.ecommerce.data

object NativeblocksRoutes {

    // Global parameter keys
    const val CAMPAIGN_ID_PARAM = "campaignId"
    const val USER_TYPE_PARAM = "userType"
    const val SOURCE_PARAM = "source"
    const val SESSION_TIMESTAMP_PARAM = "sessionTimestamp"
    const val USER_ID_PARAM = "userId"
    const val LOCATION_PARAM = "location"
    const val PREVIOUS_PURCHASES_PARAM = "previousPurchases"
    const val APP_OPEN_COUNT_PARAM = "appOpenCount"
    const val SESSION_TYPE_PARAM = "sessionType"

    // User type values
    const val USER_TYPE_ORGANIC = "organic"
    const val USER_TYPE_DEEPLINK = "deeplink"

    // Source values
    const val SOURCE_ORGANIC = "organic"
    const val SOURCE_DEEPLINK = "deeplink"

    // Session type values
    const val SESSION_TYPE_RETURNING = "returning"
    const val SESSION_TYPE_NEW = "new"

    fun getParameters(
        campaignId: String? = null,
        userType: String? = null,
        userId: String? = null,
        location: String? = null,
        previousPurchases: Int? = null,
        appOpenCount: Int? = null
    ): Array<Pair<String, String>> {
        val params = mutableListOf<Pair<String, String>>()

        // Campaign-related parameters
        if (campaignId != null) {
            params.add(CAMPAIGN_ID_PARAM to campaignId)
            params.add(SOURCE_PARAM to SOURCE_DEEPLINK)
        } else {
            params.add(SOURCE_PARAM to SOURCE_ORGANIC)
        }
        params.add(SESSION_TIMESTAMP_PARAM to System.currentTimeMillis().toString())

        // User-related parameters (add only if userType provided or they are not null)
        userType?.let { params.add(USER_TYPE_PARAM to it) }
        userId?.let { params.add(USER_ID_PARAM to it) }
        location?.let { params.add(LOCATION_PARAM to it) }
        previousPurchases?.let { params.add(PREVIOUS_PURCHASES_PARAM to it.toString()) }
        appOpenCount?.let { params.add(APP_OPEN_COUNT_PARAM to it.toString()) }

        if (previousPurchases != null) {
            params.add(SESSION_TYPE_PARAM to if (previousPurchases > 0) SESSION_TYPE_RETURNING else SESSION_TYPE_NEW)
        }

        return params.toTypedArray()
    }
}