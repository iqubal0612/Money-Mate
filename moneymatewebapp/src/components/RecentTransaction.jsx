import { ArrowRight } from "lucide-react";
import TransactionsInfoCard from "./TransactionsInfoCard";
import moment from "moment";

const RecentTransaction = ({ transactions, onMore }) => {
  return (
    <div className="bg-white shadow-md rounded-2xl p-5">
      {/* Header */}
      <div className="flex items-center justify-between">
        <h4 className="text-lg font-semibold">Recent Transaction</h4>
      </div>

      {/* Transactions */}
      <div className="mt-6 space-y-4">
        {transactions?.slice(0, 5)?.map((item) => (
          <TransactionsInfoCard
            key={item.id}
            title={item.name}
            icon={item.icon}
            date={moment(item.date).format("Do MMM YYYY")}
            amount={item.amount}
            type={item.type}
            hideDeleteBtn
          />
        ))}
      </div>
    </div>
  );
};

export default RecentTransaction;
