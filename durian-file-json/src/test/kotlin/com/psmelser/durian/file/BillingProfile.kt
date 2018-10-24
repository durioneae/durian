package com.psmelser.durian.file

import java.util.Locale
import java.util.UUID

data class BillingProfile(val id: UUID, val firstName: String, val lasName: String, val email: String, val culture: Locale)