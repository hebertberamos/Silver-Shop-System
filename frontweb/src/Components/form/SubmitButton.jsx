import PropTypes from "prop-types"

function SubmitButton({ text }) {
    return (
        <div>
            <button>{text}</button>
        </div>
    )
}

SubmitButton.propTypes = {
    text: PropTypes.string
}

export default SubmitButton