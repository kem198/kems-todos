import React from "react";

type PageProps = {
  children: React.ReactNode;
};

export function Page({ children }: PageProps) {
  return (
    <main className="flex flex-col items-center gap-8 sm:items-start">
      {children}
    </main>
  );
}
