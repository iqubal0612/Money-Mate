import { Download, Mail } from "lucide-react";
import TransactionsInfoCard from "./TransactionsInfoCard";
import moment from "moment";
import { useState } from "react";

const ExpenseList = ({ transactions, onDelete, onDownload, onEmail }) => {
  const [emailLoading, setEmailLoading] = useState(false);
  const [downloadLoading, setDownloadLoading] = useState(false);

  const handleEmail = async () => {
    setEmailLoading(true);
    try {
      await onEmail();
    } finally {
      setEmailLoading(false);
    }
  };

  const handleDownload = async () => {
    setDownloadLoading(true);
    try {
      await onDownload();
    } finally {
      setDownloadLoading(false);
    }
  };

  return (
    <div className="rounded-2xl shadow-md bg-white p-4">
      <div className="flex items-center justify-between">
        <h5 className="text-lg font-semibold">Expense List</h5>
        <div className="flex items-center gap-2">
          <button
            disabled={emailLoading}
            onClick={handleEmail}
            className="px-3 py-1 rounded-xl bg-blue-500 text-white flex items-center gap-1 hover:bg-blue-600 cursor-pointer"
          >
            {emailLoading ? (
              "Emailing..."
            ) : (
              <>
                <Mail size={15} /> Email
              </>
            )}
          </button>
          <button
            disabled={downloadLoading}
            onClick={handleDownload}
            className="px-3 py-1 rounded-xl bg-green-500 text-white flex items-center gap-1 hover:bg-green-600 cursor-pointer"
          >
            {downloadLoading ? (
              "Downloading..."
            ) : (
              <>
                <Download size={15} /> Download
              </>
            )}
          </button>
        </div>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2">
        {transactions?.map((expense) => (
          <TransactionsInfoCard
            key={expense.id}
            title={expense.name}
            icon={expense.icon}
            date={moment(expense.date).format("Do MMM YYYY")}
            amount={expense.amount}
            type="expense"
            onDelete={() => onDelete(expense.id)}
          />
        ))}
      </div>
    </div>
  );
};

export default ExpenseList;
