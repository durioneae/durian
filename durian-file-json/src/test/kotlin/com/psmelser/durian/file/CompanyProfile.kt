package com.psmelser.durian.file

import java.util.UUID

data class CompanyProfile(val tenantId: UUID, val domain: String, val companyName: String, val email: String)