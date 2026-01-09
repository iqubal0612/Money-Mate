import { Link } from "react-router-dom";

const NotFound = () => {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        flexDirection: "column",
        backgroundColor: "#f8fafc",
        textAlign: "center",
        padding: "20px",
      }}
    >
      <h1 style={{ fontSize: "6rem", margin: "0", color: "#1e293b" }}>404</h1>
      <h2 style={{ fontSize: "2rem", margin: "10px 0", color: "#334155" }}>
        Oops! Page Not Found
      </h2>
      <p style={{ color: "#64748b", marginBottom: "20px", maxWidth: "400px" }}>
        The page you are looking for might have been removed, had its name
        changed, or is temporarily unavailable.
      </p>
      <Link
        to="/"
        style={{
          textDecoration: "none",
          backgroundColor: "#2563eb",
          color: "#fff",
          padding: "12px 24px",
          borderRadius: "8px",
          fontSize: "1rem",
          fontWeight: "500",
          transition: "background 0.3s",
        }}
        onMouseOver={(e) => (e.target.style.backgroundColor = "#1d4ed8")}
        onMouseOut={(e) => (e.target.style.backgroundColor = "#2563eb")}
      >
        Go Back Home
      </Link>
    </div>
  );
};

export default NotFound;
