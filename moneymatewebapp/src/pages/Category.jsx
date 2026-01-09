import { Plus } from "lucide-react";
import Dashboard from "../components/Dashboard";
import { useUser } from "../hooks/useUser";
import CategoryList from "../components/CategoryList";
import { useEffect, useState } from "react";
import axiosConfig from "../util/AxiosConfig";
import { API_ENDPOINTS } from "../util/apiEnpoints";
import toast from "react-hot-toast";
import Modal from "../components/Modal";
import AddCatrgoryForm from "../components/AddCategoryForm";

const Category = () => {
  useUser();

  const [loading, setLoding] = useState(false);
  const [categoryData, setCategoryData] = useState([]);
  const [openAddCategorymodel, setOpenAddCategoryModel] = useState(false);
  const [openEditCategoryModel, setOpenEditCategoryModel] = useState(false);
  const [selectCategory, setSelectCategory] = useState(null);

  const fetchCategoryDetails = async () => {
    if (loading) {
      return;
    }
    setLoding(true);

    try {
      const response = await axiosConfig.get(API_ENDPOINTS.GET_ALL_CATEGORIES);
      if (response.status === 200) {
        console.log("categories", response.data);
        setCategoryData(response.data);
      }
    } catch (error) {
      console.log("Somethings went wrong. Please try again");
      toast.error(error.message);
    } finally {
      setLoding(false);
    }
  };

  useEffect(() => {
    fetchCategoryDetails();
  }, []);

  const handleAddCategory = async (category) => {
    const { name, type, icon } = category;

    if (!name.trim()) {
      toast.error("Category Name is required");
      return;
    }

    const isDuplicate = categoryData.some((category) => {
      return category.name.toLowerCase() === name.trim().toLowerCase();
    });

    if (isDuplicate) {
      toast.error("Category name already exists");
      return;
    }

    try {
      const response = await axiosConfig.post(API_ENDPOINTS.ADD_CATEGORY, {
        name,
        type,
        icon,
      });

      if (response.status === 201) {
        toast.success("Category Added successfully");
        setOpenAddCategoryModel(false);
        fetchCategoryDetails();
      }
    } catch (error) {
      console.log("Error while adding category", error);
      toast.error(error.response?.data?.message || "Failed to add actegory");
    }
  };

  const handleEditCategory = (catetogyToEdit) => {
    setSelectCategory(catetogyToEdit);
    setOpenEditCategoryModel(true);
  };

  const handleUpdateCategory = async (updatedCategory) => {
    const { id, name, type, icon } = updatedCategory;

    if (!name.trim()) {
      toast.error("Category Name is required");
      return;
    }

    if (!id) {
      toast.error("CategoryId is missing for update");
      return;
    }

    try {
      await axiosConfig.put(API_ENDPOINTS.UPDATE_CATEGORY(id), {
        name,
        type,
        icon,
      });
      setOpenEditCategoryModel(false);
      setSelectCategory(null);
      toast.success("Category Updated successfully");
      fetchCategoryDetails();
    } catch (error) {
      console.log(
        "Getting error while updating category",
        error.response?.data?.message || error.message
      );
      toast.error(error.response?.data?.message || "Failed to update category");
    }
  };

  return (
    <Dashboard activeMenu="Category">
      <div className="my-5 mx-auto">
        <div className="flex justify-between items-center mb-5">
          <h2 className="text-2xl font-semibold">All Categories</h2>
          <button
            onClick={() => setOpenAddCategoryModel(true)}
            className=" cursor-pointer flex items-center gap-1 bg-blue-500 hover:bg-blue-600 text-white px-3 py-2 rounded-md shadow"
          >
            <Plus size={15} />
            Add Category
          </button>
        </div>
        <CategoryList
          categories={categoryData}
          onEditCategory={handleEditCategory}
        />
        <Modal
          isOpen={openAddCategorymodel}
          onClose={() => setOpenAddCategoryModel(false)}
          title="Add Category"
        >
          <AddCatrgoryForm onAddCategory={handleAddCategory} />
        </Modal>
        <Modal
          onClose={() => {
            setOpenEditCategoryModel(false);
            setSelectCategory(null);
          }}
          isOpen={openEditCategoryModel}
          title="Update Category"
        >
          <AddCatrgoryForm
            initialCategoryData={selectCategory}
            onAddCategory={handleUpdateCategory}
            isEditing={true}
          />
        </Modal>
      </div>
    </Dashboard>
  );
};

export default Category;
