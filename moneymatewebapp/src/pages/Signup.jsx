import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Input from "../components/Input";
import { validateEmail } from "../util/Validation";
import axiosConfig from "../util/AxiosConfig";
import { API_ENDPOINTS } from "../util/apiEnpoints";
import toast from "react-hot-toast";
import ProfilePhotoSelector from "../components/ProfilePhotoSelector";
import uploadProfileImage from "../util/UploadProfileImage";

const Signup = () => {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [profilePhoto, setProfilePhoto] = useState(null);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    let profileImageUrl = "";
    setIsLoading(true);

    if (!fullName.trim()) {
      setError("Please enter your full name");
      setIsLoading(false);
      return;
    }

    if (!validateEmail(email)) {
      setError("Please enter a valid email address");
      setIsLoading(false);
      return;
    }

    if (!password.trim()) {
      setError("Please enter your password");
      setIsLoading(false);
      return;
    }

    setError("");

    //Sign up api call
    try {
      if (profilePhoto) {
        const imageUrl = await uploadProfileImage(profilePhoto);
        profileImageUrl = imageUrl || "";
      }
      const response = await axiosConfig.post(API_ENDPOINTS.REGISTER, {
        fullName,
        email,
        password,
        profileImageURL: profileImageUrl,
      });

      if (response.status === 201) {
        toast.success("Profile created Successfully");
        navigate("/login");
      }
    } catch (err) {
      console.log(err);

      if (err.response) {
        if (err.response.status === 403) {
          toast.error("Email already registered");
          setError("Email already registered");
        } else if (err.response.data && err.response.data.message) {
          toast.error(err.response.data.message);
          setError(err.response.data.message);
        } else {
          toast.error("Something went wrong. Please try again.");
          setError("Something went wrong. Please try again.");
        }
      } else {
        toast.error(err.message || "Something went wrong");
        setError(err.message || "Something went wrong");
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="h-screen w-full relative flex items-center justify-center overflow-hidden">
      <div className="relative z-10 w-full max-w-lg px-6">
        <div className="bg-white bg-opacity-95 backdrop-blur-sm rounded-l-lg shadow-2xl p-8">
          <h3 className="text-2xl font-semibold text-black text-center mb-2">
            Create An account
          </h3>
          <p className="text-sm text-slate-700 text-center mb-8">
            Start tracking your application
          </p>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="flex justify-center mb-6">
              <ProfilePhotoSelector
                image={profilePhoto}
                setImage={setProfilePhoto}
              />
            </div>
            <div className="grid-cols-2 md:grid-cols-2 gap-4">
              <Input
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
                label="Full Name"
                placeholder="John Doe"
                type="text"
              />
              <Input
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                label="Eamil Address"
                placeholder="xyz@gmail.com"
                type="text"
              />
              <div className="col-span-2">
                <Input
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  label="Password"
                  placeholder="******"
                  type="password"
                />
              </div>
            </div>
            {error && (
              <p className="text-red-800 text-sm text-center bg-red-50 p-2 rounded">
                {error}
              </p>
            )}
            <button
              disabled={isLoading}
              className="w-full py-3 text-lg font-medium bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
              type="submit"
            >
              {isLoading ? <>Signing Up</> : "SIGN UP"}
            </button>
            <p className="text-sm text-slate-800 text-center mt-6">
              Already have an account?
              <Link
                to="/login"
                className="font-medium text-blue-600 underline hover:text-blue-800"
              >
                Login
              </Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Signup;
