export const JsonDisplay = ({ data }: { data: unknown }) => {
  const jsonString = data ? JSON.stringify(data, undefined, 2) : " ";
  const lines = jsonString.split("\n");

  return (
    <pre className="w-full cursor-text overflow-x-auto rounded border border-gray-500 bg-gray-50 p-2 text-xs select-text">
      {lines.map((line, index) => (
        <span key={`${index}-${line}`} className="block hover:bg-gray-200/80">
          {line}
        </span>
      ))}
    </pre>
  );
};
