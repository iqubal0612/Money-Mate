import { API_ENDPOINTS } from "./apiEnpoints.js";

const CLOUDINARY_UPLOAD_PRESET = "moneymate";
const CLOUDINARY_CLOUD_NAME = "dcizq5kte";

const uploadProfileImage = async (image) => {
  const formData = new FormData();
  formData.append("file", image);
  formData.append("upload_preset", CLOUDINARY_UPLOAD_PRESET);
  formData.append("cloud_name", CLOUDINARY_CLOUD_NAME);

  try {
    const response = await fetch(API_ENDPOINTS.UPLOAD_IMAGE, {
      method: "POST",
      body: formData,
    });

    const data = await response.json();

    if (!response.ok) {
      console.error("Cloudinary error:", data);
      throw new Error(
        `Cloudinary Upload failed: ${
          data.error?.message || response.statusText
        }`
      );
    }

    console.log("Image uploaded successfully:", data.secure_url);
    return data.secure_url;
  } catch (error) {
    console.error("Error while uploading the image:", error);
    throw error;
  }
};

export default uploadProfileImage;
