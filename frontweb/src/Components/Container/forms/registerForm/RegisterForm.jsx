import PropTypes from "prop-types"
import { useState } from "react"
import Input from "../../../form/Input"
import SubmitButton from "../../../form/SubmitButton"

function RegisterForm({handleSubmit}) {
 
    const [user, setUser] = useState({})

    const submit = (e) => {
        e.preventDefault()
        handleSubmit(user)
    }

    function handleChange(e) {
        setUser({ ...user, [e.target.name]: e.target.value })
        console.log(user)
    }

    return (
        <div>
            <form onSubmit={submit}>
                <Input type="text" text="Nome" name="userName" placeholder="Insira seu nome..." handleOnChange={handleChange}/>

                <Input type="email" text="Email" name="email" placeholder="Insira seu email..." handleOnChange={handleChange}/>

                <Input type="password" text="Password" name="password" placeholder="Insira sua senha..." handleOnChange={handleChange}/>

                <SubmitButton text="Registrar" />
            </form>
        </div>
    )
}

RegisterForm.propTypes = { 
    handleSubmit: PropTypes.func
}

export default RegisterForm