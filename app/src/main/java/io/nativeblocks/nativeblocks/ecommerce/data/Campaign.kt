package io.nativeblocks.nativeblocks.ecommerce.data

data class Campaign(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val couponCode: String,
    val discountPercentage: String,
    val terms: List<String>,
    val buttonText: String = "Shop Now",
    val emoji: String = "🎉"
)

object CampaignRepository {
    private val campaigns = mapOf(
        "welcome" to Campaign(
            id = "welcome",
            title = "Welcome to Our Store!",
            subtitle = "SPECIAL OFFER!",
            description = "Get 25% OFF on your first purchase with code:",
            couponCode = "WELCOME25",
            discountPercentage = "25%",
            terms = listOf(
                "Valid for 24 hours only",
                "Minimum order $30",
                "Cannot be combined with other offers"
            ),
            emoji = "🎉"
        ),
        "flash_sale" to Campaign(
            id = "flash_sale",
            title = "Flash Sale Alert!",
            subtitle = "LIMITED TIME OFFER!",
            description = "Hurry! Get 40% OFF on all electronics with code:",
            couponCode = "FLASH40",
            discountPercentage = "40%",
            terms = listOf(
                "Valid for next 6 hours only",
                "Electronics category only",
                "Limited stock available"
            ),
            emoji = "⚡"
        ),
        "weekend" to Campaign(
            id = "weekend",
            title = "Weekend Special!",
            subtitle = "WEEKEND DEALS!",
            description = "Enjoy your weekend with 30% OFF on everything:",
            couponCode = "WEEKEND30",
            discountPercentage = "30%",
            terms = listOf(
                "Valid until Sunday midnight",
                "All categories included",
                "Free shipping on orders over $25"
            ),
            emoji = "🎯"
        ),
        "clearance" to Campaign(
            id = "clearance",
            title = "Clearance Sale!",
            subtitle = "HUGE SAVINGS!",
            description = "Massive clearance! Up to 60% OFF on selected items:",
            couponCode = "CLEAR60",
            discountPercentage = "60%",
            terms = listOf(
                "While supplies last",
                "Selected items only",
                "Final sale - no returns"
            ),
            emoji = "🔥"
        ),
        "new_user" to Campaign(
            id = "new_user",
            title = "Hello New Friend!",
            subtitle = "FIRST TIME BONUS!",
            description = "Welcome! Get 20% OFF plus free shipping:",
            couponCode = "NEWFRIEND20",
            discountPercentage = "20%",
            terms = listOf(
                "New users only",
                "Valid for 7 days",
                "Free shipping included"
            ),
            emoji = "👋"
        )
    )

    fun getCampaign(campaignId: String?): Campaign? {
        return if (campaignId.isNullOrBlank()) {
            campaigns["welcome"] // Default campaign
        } else {
            campaigns[campaignId]
        }
    }

    fun getDefaultCampaign(): Campaign {
        return campaigns["welcome"]!!
    }
}