import { Hello } from "@/components/example/hello/hello";

export default function HelloPage() {
  return (
    <div className="flex flex-col gap-4">
      <Hello />
      <Hello name="KeM198" />
    </div>
  );
}
