"use client";

import { ApiResponseData } from "@/types/example/common/api-response-data";
import { useEffect, useState } from "react";

export const GreetingCard = ({
  title,
  description,
  children,
}: {
  title: string;
  description: string;
  children: React.ReactNode;
}) => {
  return (
    <div className="flex flex-col gap-4 rounded border border-gray-200 p-4">
      <h2 className="text-lg font-semibold text-slate-800">{title}</h2>
      <p className="text-sm text-gray-600">{description}</p>
      {children}
    </div>
  );
};

export const GreetingCardContent = ({ name = "" }: { name?: string }) => {
  const [greeting, setGreeting] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");
  const [responseData, setResponseData] = useState<ApiResponseData | undefined>(
    undefined,
  );

  useEffect(() => {
    // TODO: この辺キレイにする
    const fetchGreeting = async () => {
      try {
        setLoading(true);
        setError("");

        // TODO: http://localhost:8080 を環境変数にする
        const url = name
          ? `http://localhost:8080/v1/greeting/hello?name=${encodeURIComponent(name)}`
          : "http://localhost:8080/v1/greeting/hello";
        const response = await fetch(url);
        const headers: Record<string, string> = {};
        for (const [key, value] of response.headers) {
          headers[key] = value;
        }
        const greetingText = await response.text();
        const responseInfo = {
          status: response.status,
          statusText: response.statusText,
          ok: response.ok,
          type: response.type,
          redirected: response.redirected,
          url: response.url,
          headers,
          body: greetingText,
          bodyUsed: response.bodyUsed,
        };
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        setGreeting(greetingText);
        setResponseData(responseInfo);
      } catch (error_) {
        setError(
          error_ instanceof Error ? error_.message : "Failed to fetch greeting",
        );
      } finally {
        setLoading(false);
      }
    };
    fetchGreeting();
  }, [name]);

  const success = !loading && !error;

  return (
    <div className="flex w-full flex-col gap-2">
      <GreetingStatusArea loading={loading} error={error} success={success} />
      {success && (
        <div className="flex gap-2">
          <GreetingResultArea greeting={greeting} responseData={responseData} />
        </div>
      )}
    </div>
  );
};

const GreetingStatusArea = ({
  loading,
  error,
  success,
}: {
  loading: boolean;
  error: string;
  success: boolean;
}) => {
  let statusText = "";
  let statusClass = "";
  if (loading) {
    statusText = "Loading...";
    statusClass = "text-blue-600";
  } else if (error) {
    statusText = `Error: ${error}`;
    statusClass = "text-red-600";
  } else if (success) {
    statusText = "Success";
    statusClass = "text-green-600";
  }
  return (
    <div
      className={`rounded bg-gray-100 px-4 py-2 text-sm font-semibold ${statusClass}`}
    >
      {statusText}
    </div>
  );
};

const GreetingResultArea = ({
  greeting,
  responseData,
}: {
  greeting: string;
  responseData: ApiResponseData | undefined;
}) => {
  return (
    <div className="flex w-full flex-col gap-4 rounded bg-gray-100 p-4">
      <div className="flex w-full flex-col gap-0.5">
        <h3 className="text-sm font-semibold">Message:</h3>
        <div className="text-lg">{greeting}</div>
      </div>
      <div className="flex w-full flex-col gap-0.5">
        <h3 className="text-sm font-semibold">Full Response:</h3>
        <JsonDisplay data={responseData} />
      </div>
    </div>
  );
};

const JsonDisplay = ({ data }: { data: unknown }) => {
  return (
    <pre className="cursor-text overflow-x-auto rounded border border-gray-500 bg-gray-50 p-2 text-xs select-text">
      {JSON.stringify(data, undefined, 2)}
    </pre>
  );
};
