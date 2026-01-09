import { use, useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { validateEmail } from "../util/Validation";
import Input from "../components/Input";
import { AppContext } from "../context/AppContext";
import axiosConfig from "../util/AxiosConfig";
import { API_ENDPOINTS } from "../util/apiEnpoints";
import { LoaderCircle } from "lucide-react";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const { setUser } = useContext(AppContext);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);

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

    //Login Api call
    try {
      const response = await axiosConfig.post(API_ENDPOINTS.LOGIN, {
        email,
        password,
      });
      const { token, user } = response.data;
      if (token) {
        localStorage.setItem("token", token);
        setUser(user);
        navigate("/dashboard");
      }
    } catch (error) {
      if (
        error.response &&
        (error.response.data.message || error.response.data.Message)
      ) {
        setError(error.response.data.message || error.response.data.Message);
      } else {
        console.error("Somethings went wrong", error);
        setError(error.message);
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="h-screen w-full relative flex items-center justify-center overflow-hidden">
      <div className="relative z-10 w-full max-w-lg px-6">
        <div className="bg-white bg-opacity-95 backdrop-blur-sm rounded-l-lg shadow-2xl p-8 max-h-[90vh] overflow-y-auto">
          <h3 className="text-2xl font-semibold text-black text-center mb-2">
            Welcome back!
          </h3>
          <p className="text-sm text-slate-700 text-center mb-8">
            Please enter your details.
          </p>
          <form onSubmit={handleSubmit} className="space-y-4">
            <Input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              label="Email Address"
              placeholder="xyz@gmail.com"
              type="text"
            />
            <Input
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              label="Password"
              placeholder="******"
              type="password"
            />
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
              {isLoading ? <>Logging in...</> : "LOGIN"}
            </button>
            <p className="text-sm text-slate-800 text-center mt-6">
              Don’t have an account?{" "}
              <Link
                to="/signup"
                className="font-medium text-blue-600 underline hover:text-blue-800"
              >
                Signup
              </Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
