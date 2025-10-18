export const JsonDisplay = ({ data }: { data: unknown }) => {
  return (
    <pre className="cursor-text overflow-x-auto rounded border border-gray-500 bg-gray-50 p-2 text-xs select-text">
      {JSON.stringify(data, undefined, 2)}
    </pre>
  );
};
