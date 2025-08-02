import { useState } from "react"
import axios from "axios";

function SaveNewProdut() {

    const [formData, setFormData] = useState ({
        name: "",
        price: "",
        gender: "MALE",
        type: "",
        subType: "",
        description: "",
        quantityAvailable: "",
        images: [{ imageUrl: "", mainImage: false }]
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({ ...formData, [name]: value });
    }

    const handleImageChange = (index, field, value) => {
        const newImages = [...formData.images];
        newImages[index][field] = field === "mainImage" ? value === "true" : value;
        setFormData({ ...formData, images: newImages });
    }

    // Adiciona um novo campo de imagem
    const addImageField = () => {
        setFormData({
            ...formData,
            images: [...formData.images, { imageUrl: "", mainImage: false }]
        });
    };

    // Remove um campo de imagem
    const removeImageField = (index) => {
        const newImages = formData.images.filter((_, i) => i !== index);
        setFormData({ ...formData, images: newImages });
    };

    // Envia os dados para o backend
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/product/new", formData, {
                headers: { "Content-Type": "application/json"}
            });
            alert("Produto cadastrado com sucesso!");
            console.log(response.data)
        } catch (error) {
            alert("Erro ao salvar produto: " + error.response?.data || error.message);
        }
    };


    return(
        <div>
            <form onSubmit={handleSubmit}>
            <input type="text" name="name" placeholder="Nome do Produto" value={formData.name} onChange={handleChange} required />
            <input type="number" name="price" placeholder="Preço" value={formData.price} onChange={handleChange} required />
            <select name="gender" value={formData} onChange={handleChange}>
                <option value="MALE">Masculino</option>
                <option value="FEMALE">Feminino</option>
                <option value="UNISEX">Unissex</option>
            </select>
            <input type="text" name="type" placeholder="Tipo" value={formData.type} onChange={handleChange} required />
            <input type="text" name="subType" placeholder="Subtipo" value={formData.subType} onChange={handleChange} required />
            <textarea name="description" placeholder="Descrição" value={formData.description} onChange={handleChange} required></textarea>
            <input type="number" name="quantityAvailable" placeholder="Quantidade Disponível" value={formData.quantityAvailable} onChange={handleChange} required />

            <h4>Imagens</h4>
            {formData.images.map((image, index) => (
                <div key={index} style={{ display: "flex", gap: "10px" }}>
                    <input type="text" placeholder="URL da Imagem" value={image.imageUrl} onChange={(e) => handleImageChange(index, "imageUrl", e.target.value)} required />
                    <select value={image.mainImage.toString()} onChange={(e) => handleImageChange(index, "mainImage", e.target.value)}>
                        <option value="false">Secundária</option>
                        <option value="true">Principal</option>
                    </select>
                    <button type="button" onClick={() => removeImageField(index)}>❌</button>
                </div>
            ))}
            <button type="button" onClick={addImageField}>+ Adicionar Imagem</button>

            <button type="submit">Salvar Produto</button>
            </form>
        </div>
    )
}

export default SaveNewProdut

// function AddNewProdut() {
//     return(
//         <div>
//             <h1>tela de adicioanr novo produto</h1>
//         </div>
//     )
// }

// export default AddNewProdut;
