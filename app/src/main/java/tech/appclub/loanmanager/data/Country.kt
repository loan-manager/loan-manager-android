package tech.appclub.loanmanager.data


class Country {

    var country: String? = null
    var code: String? = null
    var currency: String? = null

    override fun toString(): String {
        return "{ country: $country, code: $code, currency: $currency }"

    }

}

