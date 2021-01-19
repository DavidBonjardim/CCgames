package cidadecuriosa.emprestimos

class User {
    var userUID: String? = null
    var email: String? = null
    var password: String? = null
    var userEmprestimo1 : Emprestimo? = null
    var userEmprestimo2 : Emprestimo? = null
    var userEmprestimo3 : Emprestimo? = null
    var userEmprestimo4 : Emprestimo? = null

    constructor(userUID: String?, username: String?, password: String?) {
        this.userUID = userUID
        this.email = username
        this.password = password
    }

    constructor(username: String?, password: String?) {
        this.email = username
        this.password = password
    }

    constructor(
        userUID: String?,
        email: String?,
        password: String?,
        userEmprestimo1: Emprestimo?,
        userEmprestimo2: Emprestimo?,
        userEmprestimo3: Emprestimo?,
        userEmprestimo4: Emprestimo?
    ) {
        this.userUID = userUID
        this.email = email
        this.password = password
        this.userEmprestimo1 = userEmprestimo1
        this.userEmprestimo2 = userEmprestimo2
        this.userEmprestimo3 = userEmprestimo3
        this.userEmprestimo4 = userEmprestimo4
    }

}