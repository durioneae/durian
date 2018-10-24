package com.psmelser.durian.file

import java.util.UUID

data class Customer(val id: UUID, val companyProfile: CompanyProfile, val billingProfile: BillingProfile, val allowDelegatedAccess: Boolean = false)