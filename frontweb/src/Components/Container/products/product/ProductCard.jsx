import { Link } from "react-router-dom"
import style from './ProductCard.module.css'
import PropTypes from "prop-types"

function ProductCard({id, mainImageUrl, name, price}) {
    return (
        <Link to={`/product/${id}`} className={style.productCard} >
             <img src={mainImageUrl}alt="Product image"/>
            <div className={style.cardInformationsContent}>
                <h2>{name}</h2>
                <p>R$ {price}</p>
            </div>
        </Link>
    )
}

ProductCard.propTypes = {
    id: PropTypes.number,
    mainImageUrl: PropTypes.string,
    name: PropTypes.string,
    price: PropTypes.number
}

export default ProductCard