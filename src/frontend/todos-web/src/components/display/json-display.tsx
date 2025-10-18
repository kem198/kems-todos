export const JsonDisplay = ({ data }: { data: unknown }) => {
  return (
    <pre className="w-full cursor-text overflow-x-auto rounded border border-gray-500 bg-gray-50 p-2 text-xs select-text">
      {data ? JSON.stringify(data, undefined, 2) : "Loading..."}
    </pre>
  );
};
