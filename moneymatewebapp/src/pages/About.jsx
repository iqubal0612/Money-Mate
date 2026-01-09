export default function AboutProject() {
  return (
    <div className="min-h-screen bg-gray-50 px-6 py-12">
      <div className="max-w-5xl mx-auto">
        {/* Heading */}
        <h1 className="text-4xl font-extrabold text-center mb-6">
          About <span className="text-purple-600">Money Mate</span>
        </h1>
        <p className="text-center text-gray-600 text-lg mb-12">
          Money Manager is your personal finance companion designed to help you
          take full control of your income, expenses, and savings. With smart
          features like filtering, categories, and automated reports, financial
          management becomes effortless.
        </p>

        {/* Features Grid */}
        <div className="grid md:grid-cols-2 gap-8">
          {/* Authentication */}
          <div className="p-6 bg-white rounded-2xl shadow">
            <h3 className="text-xl font-bold mb-2"> Secure Authentication</h3>
            <p className="text-gray-600">
              Register your account, activate via email, and log in securely
              with JWT authentication to protect your financial data.
            </p>
          </div>

          {/* Income */}
          <div className="p-6 bg-white rounded-2xl shadow">
            <h3 className="text-xl font-bold mb-2"> Income Tracking</h3>
            <p className="text-gray-600">
              Add, manage, and filter your income records. Export monthly income
              reports as Excel or receive them directly via email.
            </p>
          </div>

          {/* Expense */}
          <div className="p-6 bg-white rounded-2xl shadow">
            <h3 className="text-xl font-bold mb-2"> Expense Management</h3>
            <p className="text-gray-600">
              Keep track of your expenses with filtering options and
              easy-to-manage categories. Export and share expense reports
              anytime.
            </p>
          </div>

          {/* Categories */}
          <div className="p-6 bg-white rounded-2xl shadow">
            <h3 className="text-xl font-bold mb-2"> Categories</h3>
            <p className="text-gray-600">
              Organize your incomes and expenses with customizable categories,
              and filter data based on type for better insights.
            </p>
          </div>

          {/* Excel */}
          <div className="p-6 bg-white rounded-2xl shadow">
            <h3 className="text-xl font-bold mb-2"> Excel Reports</h3>
            <p className="text-gray-600">
              Download professional Excel sheets of your monthly income and
              expenses or receive them via email instantly.
            </p>
          </div>

          {/* Filtering */}
          <div className="p-6 bg-white rounded-2xl shadow">
            <h3 className="text-xl font-bold mb-2"> Advanced Filtering</h3>
            <p className="text-gray-600">
              Filter transactions by date, keyword, type, and sort order to
              quickly analyze your financial activities.
            </p>
          </div>
        </div>

        {/* Call to Action */}
        <div className="text-center mt-12">
          <h2 className="text-2xl font-bold mb-4">
            Start Your Financial Journey Today
          </h2>
          <p className="text-gray-600 mb-6">
            Take charge of your money with our simple yet powerful tools.
          </p>
          <a
            href="/signup"
            className="bg-purple-600 text-white px-6 py-3 rounded-md font-medium"
          >
            Get Started
          </a>
        </div>
      </div>
    </div>
  );
}
